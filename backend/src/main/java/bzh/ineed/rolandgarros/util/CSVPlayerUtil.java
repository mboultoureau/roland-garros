package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.Country;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.CountryRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVPlayerUtil {
    public static String TYPE = "text/csv";
    public static String[] HEADERS = {"firstname", "lastname", "gender", "nationality", "birthDate", "birthPlace", "weight", "height", "picture", "ranking", "hand"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Person> csvToPlayers(InputStream is, CountryRepository countryRepository) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            URLValidator urlValidator = new URLValidator();

            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .builder()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .setHeader(HEADERS)
                    .build();

            CSVParser csvParser = new CSVParser(fileReader, csvFormat);
            List<Person> players = new ArrayList<Person>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Person player = new Person(csvRecord.get("firstname"), csvRecord.get("lastname"));

                String gender = csvRecord.get("gender");
                player.setGender(EGenderFormat.format(gender));
                player.setNationality(countryRepository.findByAlpha2Code(csvRecord.get("nationality")));
                player.setBirthDate(LocalDate.parse(csvRecord.get("birthDate")));
                player.setBirthPlace(csvRecord.get("birthPlace"));
                player.setWeight(Integer.parseInt(csvRecord.get("weight")));
                player.setHeight(Integer.parseInt(csvRecord.get("height")));
                player.setPicture(csvRecord.get("picture"));
                player.setRanking(Integer.parseInt(csvRecord.get("ranking")));
                player.setHand(EHandFormat.format(csvRecord.get("hand")));
                player.setIsPlayer(true);

                players.add(player);
            };

            return players;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
