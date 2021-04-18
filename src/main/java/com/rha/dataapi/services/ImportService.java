package com.rha.dataapi.services;

import com.opencsv.CSVReader;
import com.rha.dataapi.hibernate.City;
import com.rha.dataapi.hibernate.FoodCount;
import com.rha.dataapi.repositories.CityRepository;
import com.rha.dataapi.repositories.FoodCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImportService {

    private final FoodCountRepository foodCountRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;

    public Boolean importFoodCounts(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(reader);
            try {
                List<String[]> list = new ArrayList<>();
                list = csvReader.readAll();
                String[] citiesRow = list.remove(0);
                list.remove(0);
                ingestParsedCSVToDB(list, citiesRow);
                return true;
            } catch (Exception e) {
                log.error("Error while reading file", e);
            } finally {
                reader.close();
                csvReader.close();
            }
        } catch (Exception ex) {
            log.error("Error while closing file", ex);
        }
        return false;
    }

    private void ingestParsedCSVToDB(List<String[]> foodCountRows, String[] cities) throws ParseException {
        Map<Integer, City> indexCityMap = new HashMap<>();
        for (int i = 3; i < cities.length; i++) {
            String cityName = cities[i];
            Optional<City> cityInDB = cityRepository.findByName(cityName);
            if (cityInDB.isPresent()) {
                indexCityMap.put(i, cityInDB.get());
            } else {
                //create the city
                City newCity = new City();
                newCity.setName(cityName);
                newCity = cityService.create(newCity);
                indexCityMap.put(i, newCity);
            }
        }
        List<FoodCount> foodCountsToBeImported = new ArrayList<>();
        for (int timesliceIterator = 0; timesliceIterator < foodCountRows.size(); timesliceIterator++) {
            String[] currentFoodCountRow = foodCountRows.get(timesliceIterator);
            if (StringUtils.isNotBlank(currentFoodCountRow[0])) {
                LocalDate currentTimeSlice = getDateFromString(currentFoodCountRow[0]);
                for (int cityIterator = 3; cityIterator < currentFoodCountRow.length; cityIterator++) {
                    //build FoodCount object
                    City currentCity = indexCityMap.get(cityIterator);

                    if (StringUtils.isNotBlank(currentFoodCountRow[cityIterator]) && StringUtils.isNumeric(currentFoodCountRow[cityIterator])) {
                        Integer currentCityTimesliceCount = Integer.parseInt(currentFoodCountRow[cityIterator]);
                        Optional<FoodCount> foodCountToCheckInDB = foodCountRepository.findByCity_IdEqualsAndFromEqualsAndToEquals(currentCity.getId(), currentTimeSlice.plusDays(-7L), currentTimeSlice);
                        //check if it exists in the database
                        FoodCount currentCityFoodCount = formFoodCountObject(currentCity, currentTimeSlice, currentCityTimesliceCount);
                        if (foodCountToCheckInDB.isPresent() && foodCountToCheckInDB.get().getCount()!=currentCityTimesliceCount) {
                            //if yes, update.
                            FoodCount foodCountStoredInDB=foodCountToCheckInDB.get();
                            foodCountStoredInDB.copyAttributes(currentCityFoodCount);
                            foodCountsToBeImported.add(foodCountStoredInDB);
                        }else if(!foodCountToCheckInDB.isPresent()){
                            //not present in DB, create
                            foodCountsToBeImported.add(currentCityFoodCount);
                        }
                        else{
                            //everything is the same, no need to update
                        }
                    }
                }
            }
        }
        foodCountRepository.saveAll(foodCountsToBeImported);
    }

    private FoodCount formFoodCountObject(City city, LocalDate currentTimeSlice, Integer currentCityTimesliceCount) {
        FoodCount currentCityFoodCount = new FoodCount();
        currentCityFoodCount.setCity(city);
        currentCityFoodCount.setFrom(currentTimeSlice.plusDays(-7L));
        currentCityFoodCount.setTo(currentTimeSlice);
        currentCityFoodCount.setCount(currentCityTimesliceCount);
        return currentCityFoodCount;
    }

    private LocalDate getDateFromString(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("M/d/yyyy"));
        //return DateUtils.parseDate(dateString, "MM/dd/yyyy", "M/dd/yyyy");
    }
}
