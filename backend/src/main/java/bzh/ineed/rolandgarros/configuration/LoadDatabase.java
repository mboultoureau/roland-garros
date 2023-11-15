package bzh.ineed.rolandgarros.configuration;

import bzh.ineed.rolandgarros.controller.TournamentController;
import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.repository.*;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private void affectsScoresToMatch(Match match, ScoreRepository scoreRepository, Integer[] scoreTeamA, Integer[] scoreTeamB) {
        for (int i = 0; i < scoreTeamA.length; i++) {
            Score score = new Score(i + 1, scoreTeamA[i], scoreTeamB[i], match);
            scoreRepository.save(score);
        }
    }

    private void createMatches(Tournament tournament, EStatus eStatus, EType eType, MatchRepository matchRepository) {
        ArrayList<Match> matches = new ArrayList<>();

        matches.add(new Match(EStatus.UNDEFINED, ERound.FINAL_ROUND, eType, tournament));

        for (Integer i = 0; i < 2; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.SEMI_FINAL, eType, tournament));
        }

        for (Integer i = 0; i < 4; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.QUART_FINAL, eType, tournament));
        }

        for (Integer i = 0; i < 8; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.SIXTEENTH_ROUND, eType, tournament));
        }

        for (Integer i = 0; i < 16; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.THIRD_ROUND, eType, tournament));
        }

        for (Integer i = 0; i < 32; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.SECOND_ROUND, eType, tournament));
        }

        for (Integer i = 0; i < 64; i++) {
            matches.add(new Match(EStatus.UNDEFINED, ERound.FIRST_ROUND, eType, tournament));
        }

        matchRepository.saveAll(matches);
        log.info("[MATCH] " + matches.size() + " matches created with status " + eStatus + " and type " + eType);
    }

    public void affectTeamsToMatch(Match match, Team teamA, Team teamB, TeamRepository teamRepository, MatchRepository matchRepository) {
        teamA = teamRepository.save(teamA);
        teamB = teamRepository.save(teamB);

        match.setTeamA(teamA);
        match.setTeamB(teamB);

        matchRepository.save(match);
    }

    @Bean
    CommandLineRunner initDatabase(
            CountryRepository countryRepository,
            PersonRepository personRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            CourtRepository courtRepository,
            TrainingRepository trainingRepository,
            TournamentRepository tournamentRepository,
            MatchRepository matchRepository,
            TeamRepository teamRepository,
            ScoreRepository scoreRepository
    ) {
        return args -> {
            // DELETE ALL
            log.info("[SCORE] Delete all");
            scoreRepository.deleteAll();

            log.info("[TRAINING] Delete all");
            trainingRepository.deleteAll();

            log.info("[MATCH] Delete all");
            matchRepository.deleteAll();

            log.info("[TEAM] Delete all");
            teamRepository.deleteAll();

            log.info("[TOURNAMENT] Delete all");
            tournamentRepository.deleteAll();

            log.info("[USER] Delete all");
            userRepository.deleteAll();

            log.info("[PERSON] Delete all");
            personRepository.deleteAll();

            log.info("[COUNTRY] Delete all");
            countryRepository.deleteAll();

            log.info("[ROLE] Delete all");
            roleRepository.deleteAll();

            log.info("[COURT] Delete all");
            courtRepository.deleteAll();

            // COUNTRIES
            ArrayList<Country> countries = new ArrayList<>();

            countries.add(new Country("Afghanistan", "AF", "AFG"));
            countries.add(new Country("Albania", "AL", "ALB"));
            countries.add(new Country("Algeria", "DZ", "DZA"));
            countries.add(new Country("American Samoa", "AS", "ASM"));
            countries.add(new Country("Andorra", "AD", "AND"));
            countries.add(new Country("Angola", "AO", "AGO"));
            countries.add(new Country("Anguilla", "AI", "AIA"));
            countries.add(new Country("Antarctica", "AQ", "ATA"));
            countries.add(new Country("Antigua and Barbuda", "AG", "ATG"));
            countries.add(new Country("Argentina", "AR", "ARG"));
            countries.add(new Country("Armenia", "AM", "ARM"));
            countries.add(new Country("Aruba", "AW", "ABW"));
            countries.add(new Country("Australia", "AU", "AUS"));
            countries.add(new Country("Austria", "AT", "AUT"));
            countries.add(new Country("Azerbaijan", "AZ", "AZE"));
            countries.add(new Country("Bahamas", "BS", "BHS"));
            countries.add(new Country("Bahrain", "BH", "BHR"));
            countries.add(new Country("Bangladesh", "BD", "BGD"));
            countries.add(new Country("Barbados", "BB", "BRB"));
            countries.add(new Country("Belarus", "BY", "BLR"));
            countries.add(new Country("Belgium", "BE", "BEL"));
            countries.add(new Country("Belize", "BZ", "BLZ"));
            countries.add(new Country("Benin", "BJ", "BEN"));
            countries.add(new Country("Bermuda", "BM", "BMU"));
            countries.add(new Country("Bhutan", "BT", "BTN"));
            countries.add(new Country("Bolivia", "BO", "BOL"));
            countries.add(new Country("Bosnia and Herzegovina", "BA", "BIH"));
            countries.add(new Country("Botswana", "BW", "BWA"));
            countries.add(new Country("Bouvet Island", "BV", "BVT"));
            countries.add(new Country("Brazil", "BR", "BRA"));
            countries.add(new Country("British Indian Ocean Territory", "IO", "IOT"));
            countries.add(new Country("Brunei", "BN", "BRN"));
            countries.add(new Country("Bulgaria", "BG", "BGR"));
            countries.add(new Country("Burkina Faso", "BF", "BFA"));
            countries.add(new Country("Burundi", "BI", "BDI"));
            countries.add(new Country("Cambodia", "KH", "KHM"));
            countries.add(new Country("Cameroon", "CM", "CMR"));
            countries.add(new Country("Canada", "CA", "CAN"));
            countries.add(new Country("Cape Verde", "CV", "CPV"));
            countries.add(new Country("Cayman Islands", "KY", "CYM"));
            countries.add(new Country("Central African Republic", "CF", "CAF"));
            countries.add(new Country("Chad", "TD", "TCD"));
            countries.add(new Country("Chile", "CL", "CHL"));
            countries.add(new Country("China", "CN", "CHN"));
            countries.add(new Country("Christmas Island", "CX", "CXR"));
            countries.add(new Country("Cocos (Keeling) Islands", "CC", "CCK"));
            countries.add(new Country("Colombia", "CO", "COL"));
            countries.add(new Country("Comoros", "KM", "COM"));
            countries.add(new Country("Congo", "CG", "COG"));
            countries.add(new Country("Cook Islands", "CK", "COK"));
            countries.add(new Country("Costa Rica", "CR", "CRI"));
            countries.add(new Country("Ivory Coast", "CI", "CIV"));
            countries.add(new Country("Croatia", "HR", "HRV"));
            countries.add(new Country("Cuba", "CU", "CUB"));
            countries.add(new Country("Cyprus", "CY", "CYP"));
            countries.add(new Country("Czech Republic", "CZ", "CZE"));
            countries.add(new Country("Denmark", "DK", "DNK"));
            countries.add(new Country("Djibouti", "DJ", "DJI"));
            countries.add(new Country("Dominica", "DM", "DMA"));
            countries.add(new Country("Dominican Republic", "DO", "DOM"));
            countries.add(new Country("Ecuador", "EC", "ECU"));
            countries.add(new Country("Egypt", "EG", "EGY"));
            countries.add(new Country("El Salvador", "SV", "SLV"));
            countries.add(new Country("Equatorial Guinea", "GQ", "GNQ"));
            countries.add(new Country("Eritrea", "ER", "ERI"));
            countries.add(new Country("Estonia", "EE", "EST"));
            countries.add(new Country("Ethiopia", "ET", "ETH"));
            countries.add(new Country("Falkland Islands (Malvinas)", "FK", "FLK"));
            countries.add(new Country("Faroe Islands", "FO", "FRO"));
            countries.add(new Country("Fiji", "FJ", "FJI"));
            countries.add(new Country("Finland", "FI", "FIN"));
            countries.add(new Country("France", "FR", "FRA"));
            countries.add(new Country("French Guiana", "GF", "GUF"));
            countries.add(new Country("French Polynesia", "PF", "PYF"));
            countries.add(new Country("French Southern Territories", "TF", "ATF"));
            countries.add(new Country("Gabon", "GA", "GAB"));
            countries.add(new Country("Gambia", "GM", "GMB"));
            countries.add(new Country("Georgia", "GE", "GEO"));
            countries.add(new Country("Germany", "DE", "DEU"));
            countries.add(new Country("Ghana", "GH", "GHA"));
            countries.add(new Country("Gibraltar", "GI", "GIB"));
            countries.add(new Country("Greece", "GR", "GRE"));
            countries.add(new Country("Greenland", "GL", "GRL"));
            countries.add(new Country("Grenada", "GD", "GRD"));
            countries.add(new Country("Guadeloupe", "GP", "GLP"));
            countries.add(new Country("Guam", "GU", "GUM"));
            countries.add(new Country("Guatemala", "GT", "GTM"));
            countries.add(new Country("Guernsey", "GG", "GGY"));
            countries.add(new Country("Guinea", "GN", "GIN"));
            countries.add(new Country("Guinea-Bissau", "GW", "GNB"));
            countries.add(new Country("Guyana", "GY", "GUY"));
            countries.add(new Country("Haiti", "HT", "HTI"));
            countries.add(new Country("Heard Island and McDonald Islands", "HM", "HMD"));
            countries.add(new Country("Holy See (Vatican City State)", "VA", "VAT"));
            countries.add(new Country("Honduras", "HN", "HND"));
            countries.add(new Country("Hong Kong", "HK", "HKG"));
            countries.add(new Country("Hungary", "HU", "HUN"));
            countries.add(new Country("Iceland", "IS", "ISL"));
            countries.add(new Country("India", "IN", "IND"));
            countries.add(new Country("Indonesia", "ID", "IDN"));
            countries.add(new Country("Iran", "IR", "IRA"));
            countries.add(new Country("Iraq", "IQ", "IRQ"));
            countries.add(new Country("Ireland", "IE", "IRL"));
            countries.add(new Country("Isle of Man", "IM", "IMN"));
            countries.add(new Country("Israel", "IL", "ISR"));
            countries.add(new Country("Italy", "IT", "ITA"));
            countries.add(new Country("Jamaica", "JM", "JAM"));
            countries.add(new Country("Japan", "JP", "JPN"));
            countries.add(new Country("Jersey", "JE", "JEY"));
            countries.add(new Country("Jordan", "JO", "JOR"));
            countries.add(new Country("Kazakhstan", "KZ", "KAZ"));
            countries.add(new Country("Kenya", "KE", "KEN"));
            countries.add(new Country("Kiribati", "KI", "KIR"));
            countries.add(new Country("South Korea", "KR", "KOR"));
            countries.add(new Country("Kuwait", "KW", "KWT"));
            countries.add(new Country("Kyrgyzstan", "KG", "KGZ"));
            countries.add(new Country("Lao People's Democratic Republic", "LA", "LAO"));
            countries.add(new Country("Latvia", "LV", "LVA"));
            countries.add(new Country("Lebanon", "LB", "LBN"));
            countries.add(new Country("Lesotho", "LS", "LSO"));
            countries.add(new Country("Liberia", "LR", "LBR"));
            countries.add(new Country("Libya", "LY", "LBY"));
            countries.add(new Country("Liechtenstein", "LI", "LIE"));
            countries.add(new Country("Lithuania", "LT", "LTU"));
            countries.add(new Country("Luxembourg", "LU", "LUX"));
            countries.add(new Country("Macao", "MO", "MAC"));
            countries.add(new Country("Macedonia", "MK", "MKD"));
            countries.add(new Country("Madagascar", "MG", "MDG"));
            countries.add(new Country("Malawi", "MW", "MWI"));
            countries.add(new Country("Malaysia", "MY", "MYS"));
            countries.add(new Country("Maldives", "MV", "MDV"));
            countries.add(new Country("Mali", "ML", "MLI"));
            countries.add(new Country("Malta", "MT", "MLT"));
            countries.add(new Country("Marshall Islands", "MH", "MHL"));
            countries.add(new Country("Martinique", "MQ", "MTQ"));
            countries.add(new Country("Mauritania", "MR", "MRT"));
            countries.add(new Country("Mauritius", "MU", "MUS"));
            countries.add(new Country("Mayotte", "YT", "MYT"));
            countries.add(new Country("Mexico", "MX", "MEX"));
            countries.add(new Country("Monaco", "MC", "MCO"));
            countries.add(new Country("Mongolia", "MN", "MNG"));
            countries.add(new Country("Montenegro", "ME", "MNE"));
            countries.add(new Country("Montserrat", "MS", "MSR"));
            countries.add(new Country("Morocco", "MA", "MAR"));
            countries.add(new Country("Mozambique", "MZ", "MOZ"));
            countries.add(new Country("Myanmar", "MM", "MMR"));
            countries.add(new Country("Namibia", "NA", "NAM"));
            countries.add(new Country("Nauru", "NR", "NRU"));
            countries.add(new Country("Nepal", "NP", "NPL"));
            countries.add(new Country("Netherlands", "NL", "NLD"));
            countries.add(new Country("Netherlands Antilles", "AN", "ANT"));
            countries.add(new Country("New Caledonia", "NC", "NCL"));
            countries.add(new Country("New Zealand", "NZ", "NZL"));
            countries.add(new Country("Nicaragua", "NI", "NIC"));
            countries.add(new Country("Niger", "NE", "NER"));
            countries.add(new Country("Nigeria", "NG", "NGA"));
            countries.add(new Country("Niue", "NU", "NIU"));
            countries.add(new Country("Norfolk Island", "NF", "NFK"));
            countries.add(new Country("Northern Mariana Islands", "MP", "MNP"));
            countries.add(new Country("Norway", "NO", "NOR"));
            countries.add(new Country("Oman", "OM", "OMN"));
            countries.add(new Country("Pakistan", "PK", "PAK"));
            countries.add(new Country("Palau", "PW", "PLW"));
            countries.add(new Country("Panama", "PA", "PAN"));
            countries.add(new Country("Papua New Guinea", "PG", "PNG"));
            countries.add(new Country("Paraguay", "PY", "PRY"));
            countries.add(new Country("Peru", "PE", "PER"));
            countries.add(new Country("Philippines", "PH", "PHL"));
            countries.add(new Country("Pitcairn", "PN", "PCN"));
            countries.add(new Country("Poland", "PL", "POL"));
            countries.add(new Country("Portugal", "PT", "PRT"));
            countries.add(new Country("Puerto Rico", "PR", "PRI"));
            countries.add(new Country("Qatar", "QA", "QAT"));
            countries.add(new Country("Réunion", "RE", "REU"));
            countries.add(new Country("Romania", "RO", "ROU"));
            countries.add(new Country("Russia", "RU", "RUS"));
            countries.add(new Country("Rwanda", "RW", "RWA"));
            countries.add(new Country("Saint Kitts and Nevis", "KN", "KNA"));
            countries.add(new Country("Saint Lucia", "LC", "LCA"));
            countries.add(new Country("Saint Pierre and Miquelon", "PM", "SPM"));
            countries.add(new Country("St. Vincent and the Grenadines", "VC", "VCT"));
            countries.add(new Country("Samoa", "WS", "WSM"));
            countries.add(new Country("San Marino", "SM", "SMR"));
            countries.add(new Country("Sao Tome and Principe", "ST", "STP"));
            countries.add(new Country("Saudi Arabia", "SA", "SAU"));
            countries.add(new Country("Senegal", "SN", "SEN"));
            countries.add(new Country("Serbia", "RS", "SRB"));
            countries.add(new Country("Seychelles", "SC", "SYC"));
            countries.add(new Country("Sierra Leone", "SL", "SLE"));
            countries.add(new Country("Singapore", "SG", "SGP"));
            countries.add(new Country("Slovakia", "SK", "SVK"));
            countries.add(new Country("Slovenia", "SI", "SVN"));
            countries.add(new Country("Solomon Islands", "SB", "SLB"));
            countries.add(new Country("Somalia", "SO", "SOM"));
            countries.add(new Country("South Africa", "ZA", "ZAF"));
            countries.add(new Country("South Georgia and the South Sandwich Islands", "GS", "SGS"));
            countries.add(new Country("South Sudan", "SS", "SSD"));
            countries.add(new Country("Spain", "ES", "ESP"));
            countries.add(new Country("Sri Lanka", "LK", "LKA"));
            countries.add(new Country("Sudan", "SD", "SDN"));
            countries.add(new Country("Suriname", "SR", "SUR"));
            countries.add(new Country("Svalbard and Jan Mayen", "SJ", "SJM"));
            countries.add(new Country("Swaziland", "SZ", "SWZ"));
            countries.add(new Country("Sweden", "SE", "SWE"));
            countries.add(new Country("Switzerland", "CH", "CHE"));
            countries.add(new Country("Syrian Arab Republic", "SY", "SYR"));
            countries.add(new Country("Taiwan", "TW", "TWN"));
            countries.add(new Country("Tajikistan", "TJ", "TJK"));
            countries.add(new Country("Thailand", "TH", "THA"));
            countries.add(new Country("Timor-Leste", "TL", "TLS"));
            countries.add(new Country("Togo", "TG", "TGO"));
            countries.add(new Country("Tokelau", "TK", "TKL"));
            countries.add(new Country("Tonga", "TO", "TON"));
            countries.add(new Country("Trinidad and Tobago", "TT", "TTO"));
            countries.add(new Country("Tunisia", "TN", "TUN"));
            countries.add(new Country("Turkey", "TR", "TUR"));
            countries.add(new Country("Turkmenistan", "TM", "TKM"));
            countries.add(new Country("Turks and Caicos Islands", "TC", "TCA"));
            countries.add(new Country("Tuvalu", "TV", "TUV"));
            countries.add(new Country("Uganda", "UG", "UGA"));
            countries.add(new Country("Ukraine", "UA", "UKR"));
            countries.add(new Country("United Arab Emirates", "AE", "ARE"));
            countries.add(new Country("United Kingdom", "GB", "GBR"));
            countries.add(new Country("United States", "US", "USA"));
            countries.add(new Country("United States Minor Outlying Islands", "UM", "UMI"));
            countries.add(new Country("Uruguay", "UY", "URY"));
            countries.add(new Country("Uzbekistan", "UZ", "UZB"));
            countries.add(new Country("Vanuatu", "VU", "VUT"));
            countries.add(new Country("Venezuela", "VE", "VEN"));
            countries.add(new Country("Vietnam", "VN", "VNM"));
            countries.add(new Country("Wallis and Futuna", "WF", "WLF"));
            countries.add(new Country("Western Sahara", "EH", "ESH"));
            countries.add(new Country("Yemen", "YE", "YEM"));
            countries.add(new Country("Zambia", "ZM", "ZMB"));
            countries.add(new Country("Zimbabwe", "ZW", "ZWE"));

            log.info("[COUNTRY] Loaded " + countries.size() + " countries");
            countryRepository.saveAll(countries);


            // PERSONS

            // Coachs
            Person coach1 = new Person("Tomasz", "Wiktorowski");
            coach1.setGender(EGender.MALE);
            coach1.setIsCoach(true);
            coach1.setIsPlayer(false);

            log.info("[PERSON] Preloading " + personRepository.save(coach1));

            Person coach2 = new Person("Issam", "Jellali");
            coach2.setGender(EGender.MALE);
            coach2.setIsCoach(true);
            coach2.setIsPlayer(false);

            log.info("[PERSON] Preloading " + personRepository.save(coach2));

            // PLAYERS
            ArrayList<Person> players = new ArrayList<>();

            // Womans
            Person playerF1 = new Person("Iga", "Swiatek");
            playerF1.setGender(EGender.FEMALE);
            playerF1.setBirthDate(LocalDate.parse("2001-05-31"));
            playerF1.setHeight(175);
            playerF1.setBirthPlace("Varsosie, Pologne");
            playerF1.setIsCoach(false);
            playerF1.setIsPlayer(true);
            playerF1.setHand(EHand.RIGHT);
            playerF1.setPicture("https://images.prismic.io/fft-rg-site/1a1fe64f-69f1-429d-a6d2-1be8f110be47_20200308_PJ_SwiatekIga_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF1.setRanking(1);
            playerF1.setNationality(countryRepository.findByName("Poland"));
            playerF1.setCoach(personRepository.findByFirstnameAndLastname("Tomasz", "Wiktorowski").get());

            Person playerF2 = new Person("Ons", "Jabeur");
            playerF2.setGender(EGender.FEMALE);
            playerF2.setBirthDate(LocalDate.parse("1994-08-28"));
            playerF2.setBirthPlace("Ksar Hellal, Tunisie");
            playerF2.setHeight(167);
            playerF2.setHand(EHand.RIGHT);
            playerF2.setIsCoach(false);
            playerF2.setIsPlayer(true);
            playerF2.setPicture("https://images.prismic.io/fft-rg-site/0942c177-cde4-4f29-8c11-5ec5a19df99e_20220427_PJ_JabeurOns_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF2.setRanking(2);
            playerF2.setNationality(countryRepository.findByName("Tunisia"));
            playerF2.setCoach(personRepository.findByFirstnameAndLastname("Issam", "Jellali").get());

            Person playerF3 = new Person("Jessica", "Pegula");
            playerF3.setGender(EGender.FEMALE);
            playerF3.setBirthPlace("Buffalo, New York, Etats-Unis");
            playerF3.setBirthDate(LocalDate.parse("1994-02-24"));
            playerF3.setHeight(170);
            playerF3.setHand(EHand.RIGHT);
            playerF3.setIsCoach(false);
            playerF3.setIsPlayer(true);
            playerF3.setPicture("https://images.prismic.io/fft-rg-site/39fed296-454c-445b-8988-0390211d9626_20220427_PJ_PegulaJessica_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF3.setRanking(3);
            playerF3.setNationality(countryRepository.findByName("United States"));

            Person playerF4 = new Person("Caroline", "Garcia");
            playerF4.setGender(EGender.FEMALE);
            playerF4.setBirthDate(LocalDate.parse("1993-10-16"));
            playerF4.setBirthPlace("Saint-Germain-en-Laye, France");
            playerF4.setHeight(177);
            playerF4.setHand(EHand.RIGHT);
            playerF4.setIsCoach(false);
            playerF4.setIsPlayer(true);
            playerF4.setPicture("https://images.prismic.io/fft-rg-site/3dbbc6e2-ed47-4a23-af86-495a4c9e2ca8_20180524_PJ_GarciaCaroline_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF4.setRanking(4);
            playerF4.setNationality(countryRepository.findByName("France"));

            Person playerF5 = new Person("Aryna", "Sabalenka");
            playerF5.setGender(EGender.FEMALE);
            playerF5.setBirthPlace("Minsk, Biélorussie");
            playerF5.setBirthDate(LocalDate.parse("1998-05-05"));
            playerF5.setHeight(182);
            playerF5.setHand(EHand.RIGHT);
            playerF5.setIsCoach(false);
            playerF5.setIsPlayer(false);
            playerF5.setPicture("https://images.prismic.io/fft-rg-site/f38acf23-789e-4d62-b39d-0cf22797e1a3_20190302_PJ_SabalenkaAryna_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF5.setRanking(5);
            playerF5.setNationality(countryRepository.findByName("Belarus"));

            Person playerF6 = new Person("Maria", "Sakkari");
            playerF6.setGender(EGender.FEMALE);
            playerF6.setBirthPlace("Athènes, Grèce");
            playerF6.setBirthDate(LocalDate.parse("1995-08-25"));
            playerF6.setHeight(172);
            playerF6.setHand(EHand.RIGHT);
            playerF6.setIsCoach(false);
            playerF6.setIsPlayer(true);
            playerF6.setPicture("https://images.prismic.io/fft-rg-site/f2be46ed-3c69-4f45-bd34-f4c2ea568929_20200311_PJ_SakkariMaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF6.setRanking(6);
            playerF6.setNationality(countryRepository.findByName("Greece"));

            Person playerF7 = new Person("Coco", "Gauff");
            playerF7.setGender(EGender.FEMALE);
            playerF7.setBirthDate(LocalDate.parse("2004-03-13"));
            playerF7.setBirthPlace("Delray Beach, Fl, États-Unis");
            playerF7.setHeight(175);
            playerF7.setHand(EHand.RIGHT);
            playerF7.setIsCoach(false);
            playerF7.setIsPlayer(true);
            playerF7.setPicture("https://images.prismic.io/fft-rg-site/2faad022-abf7-48e4-842a-34534366b5fd_20200310_PJ_GauffCory_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF7.setRanking(7);
            playerF7.setNationality(countryRepository.findByName("United States"));

            Person playerF8 = new Person("Daria", "Kasatkina");
            playerF8.setGender(EGender.FEMALE);
            playerF8.setBirthPlace("Togliatti, Russie");
            playerF8.setBirthDate(LocalDate.parse("1997-05-07"));
            playerF8.setHeight(170);
            playerF8.setIsCoach(false);
            playerF8.setIsPlayer(true);
            playerF8.setPicture("https://images.prismic.io/fft-rg-site/45777401-3e3b-459c-98d9-dc54f00547e9_20190306_PJ_KasatkinaDaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerF8.setRanking(8);
            playerF8.setNationality(countryRepository.findByName("Russia"));

            // Mans
            Person playerM1 = new Person("Carlos", "Alcaraz");
            playerM1.setGender(EGender.MALE);
            playerM1.setHand(EHand.RIGHT);
            playerM1.setBirthPlace("El Palmar, Murcia, Espagne");
            playerM1.setBirthDate(LocalDate.parse("2003-05-05"));
            playerM1.setHeight(182);
            playerM1.setWeight(74);
            playerM1.setIsCoach(false);
            playerM1.setIsPlayer(true);
            playerM1.setEarlyCareer(LocalDate.parse("2018-01-01"));
            playerM1.setPicture("https://images.prismic.io/fft-rg-site/6ca816f6-ef3f-4633-9a97-155651ca4857_20220308_PJ_AlcarazCarlos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM1.setRanking(1);
            playerM1.setNationality(countryRepository.findByName("Spain"));

            Person playerM2 = new Person("Rafael", "Nadal");
            playerM2.setGender(EGender.MALE);
            playerM2.setBirthPlace("Manacor, Espagne");
            playerM2.setBirthDate(LocalDate.parse("1986-06-03"));
            playerM2.setEarlyCareer(LocalDate.parse("2001-01-01"));
            playerM2.setHand(EHand.LEFT);
            playerM2.setHeight(185);
            playerM2.setWeight(84);
            playerM2.setIsCoach(false);
            playerM2.setIsPlayer(true);
            playerM2.setPicture("https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM2.setRanking(2);
            playerM2.setNationality(countryRepository.findByName("Spain"));

            Person playerM3 = new Person("Stefanos", "Tsitsipas");
            playerM3.setGender(EGender.MALE);
            playerM3.setBirthPlace("Athènes, Grèce");
            playerM3.setBirthDate(LocalDate.parse("1998-08-12"));
            playerM3.setHand(EHand.RIGHT);
            playerM3.setEarlyCareer(LocalDate.parse("2016-01-01"));
            playerM3.setHeight(193);
            playerM3.setWeight(89);
            playerM3.setIsCoach(false);
            playerM3.setIsPlayer(true);
            playerM3.setPicture("https://images.prismic.io/fft-rg-site/6bbfe32a-28dd-4c05-9499-fd96dd1b10d0_20200119_PJ_TsitsipasStephanos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            playerM3.setRanking(3);
            playerM3.setNationality(countryRepository.findByName("Greece"));

            players.add(playerF1);
            players.add(playerF2);
            players.add(playerF3);
            players.add(playerF4);
            players.add(playerF5);
            players.add(playerF6);
            players.add(playerF7);
            players.add(playerF8);
            players.add(playerM1);
            players.add(playerM2);
            players.add(playerM3);

            personRepository.saveAll(players);
            log.info("[PLAYER] - Players added");

            // ROLES
            Role roleUser = new Role(ERole.ROLE_USER);
            Role roleEditorPlayer = new Role(ERole.ROLE_EDITOR_PLAYER);
            Role roleEditorMatch = new Role(ERole.ROLE_EDITOR_MATCH);
            Role roleAdmin = new Role(ERole.ROLE_ADMIN);

            log.info("[ROLE] Preloading " + roleRepository.save(roleUser));
            log.info("[ROLE] Preloading " + roleRepository.save(roleEditorPlayer));
            log.info("[ROLE] Preloading " + roleRepository.save(roleEditorMatch));
            log.info("[ROLE] Preloading " + roleRepository.save(roleAdmin));

            // USERS
            User admin = new User("admin", "admin@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles1 = new HashSet<>();
            adminRoles1.add(roleRepository.findByName(ERole.ROLE_ADMIN));
            admin.setRoles(adminRoles1);

            User adminMatch = new User("admin-match", "admin1@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles2 = new HashSet<>();
            adminRoles2.add(roleRepository.findByName(ERole.ROLE_EDITOR_MATCH));
            adminMatch.setRoles(adminRoles2);

            User adminPlayer = new User("admin-player", "admin2@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");
            HashSet<Role> adminRoles3 = new HashSet<>();
            adminRoles3.add(roleRepository.findByName(ERole.ROLE_EDITOR_PLAYER));
            adminPlayer.setRoles(adminRoles3);

            User adminNoRule = new User("admin-norole", "admin3@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m");

            log.info("[USER] Preloading " + userRepository.save(admin));
            log.info("[USER] Preloading " + userRepository.save(adminPlayer));
            log.info("[USER] Preloading " + userRepository.save(adminMatch));
            log.info("[USER] Preloading " + userRepository.save(adminNoRule));

            // COURTS

            Court court1 = new Court("Court Leane");
            Court court2 = new Court("Court Mathis");
            Court court3 = new Court("Court Sylvain");
            Court court4 = new Court("Court Paul");

            log.info("[COURT] Preloading " + courtRepository.save(court1));
            log.info("[COURT] Preloading " + courtRepository.save(court2));
            log.info("[COURT] Preloading " + courtRepository.save(court3));
            log.info("[COURT] Preloading " + courtRepository.save(court4));

            // TRAININGS

            Training training1 = new Training();
            training1.setDetails("Entrainement du samedi matin");
            training1.setDuration(2);
            training1.setStartDate(LocalDateTime.parse("2022-11-26T10:00:00"));
            training1.setPlayer(personRepository.findByFirstnameAndLastname("Ons", "Jabeur").get());
            training1.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training1));

            Training training2 = new Training();
            training2.setDetails("Entrainement du samedi midi");
            training2.setDuration(2);
            training2.setStartDate(LocalDateTime.parse("2022-11-26T12:00:00"));
            training2.setPlayer(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get());
            training2.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training2));

            Training training3 = new Training();
            training3.setDetails("Entrainement du lundi soir");
            training3.setDuration(2);
            training3.setStartDate(LocalDateTime.parse("2022-11-28T12:00:00"));
            training3.setPlayer(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get());
            training3.setCourt(courtRepository.findByName("Court Leane").get());

            log.info("[TRAINING] Preloading " + trainingRepository.save(training3));

            // TOURNAMENTS
            TournamentController tournamentController = new TournamentController();

            Tournament tournament1 = new Tournament(2022);
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(tournament1));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2023)));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2021)));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2020)));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2019)));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2018)));
            log.info("[TOURNAMENT] Preloading " + tournamentRepository.save(new Tournament(2017)));

            // Create matches
            createMatches(tournament1, EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournament1, EStatus.UNDEFINED, EType.SIMPLE_WOMEN, matchRepository);
            createMatches(tournament1, EStatus.UNDEFINED, EType.DOUBLE_WOMAN, matchRepository);
            createMatches(tournament1, EStatus.UNDEFINED, EType.DOUBLE_MEN, matchRepository);
            createMatches(tournament1, EStatus.UNDEFINED, EType.MIXED, matchRepository);
            createMatches(tournamentRepository.findByYear(2023).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournamentRepository.findByYear(2021).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournamentRepository.findByYear(2020).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournamentRepository.findByYear(2019).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournamentRepository.findByYear(2018).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);
            createMatches(tournamentRepository.findByYear(2017).get(), EStatus.UNDEFINED, EType.SIMPLE_MEN, matchRepository);

            // Fixtures for tournament 2022

            // FINAL MATCH
            Match finalMatch = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.FINAL_ROUND).get(0);
            Team teamA = new Team(personRepository.findByFirstnameAndLastname("Ons", "Jabeur").get(), null);
            Team teamB = new Team(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get(), null);

            affectTeamsToMatch(finalMatch, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(finalMatch, scoreRepository, new Integer[]{7, 1, 6, 2, 3}, new Integer[]{3, 6, 1, 7, 7});

            finalMatch.setWinner(teamA);
            finalMatch.setTeamA(teamA);
            finalMatch.setTeamB(teamB);
            finalMatch.setDuration(5);

            matchRepository.save(finalMatch);

            // SEMI-FINAL 1
            Match semiFinalMatch1 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.SEMI_FINAL).get(0);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Ons", "Jabeur").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Jessica", "Pegula").get(), null);

            affectTeamsToMatch(semiFinalMatch1, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(semiFinalMatch1, scoreRepository, new Integer[]{2, 7, 3, 7, 6}, new Integer[]{6, 3, 7, 4, 1});

            semiFinalMatch1.setWinner(teamA);
            semiFinalMatch1.setTeamA(teamA);
            semiFinalMatch1.setTeamB(teamB);
            semiFinalMatch1.setDuration(2);

            matchRepository.save(semiFinalMatch1);

            // SEMI-FINAL 2
            Match semiFinalMatch2 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.SEMI_FINAL).get(1);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Caroline", "Garcia").get(), null);

            affectTeamsToMatch(semiFinalMatch2, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(semiFinalMatch2, scoreRepository, new Integer[]{3, 6, 1, 7, 7}, new Integer[]{7, 1, 6, 2, 3});

            semiFinalMatch2.setWinner(teamA);
            semiFinalMatch2.setTeamA(teamA);
            semiFinalMatch2.setTeamB(teamB);
            semiFinalMatch1.setDuration(4);

            matchRepository.save(semiFinalMatch2);

            // QUARTER ROUND 1
            Match quarterRound1 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.QUART_FINAL).get(0);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Ons", "Jabeur").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Iga", "Swiatek").get(), null);

            affectTeamsToMatch(quarterRound1, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(quarterRound1, scoreRepository, new Integer[]{3, 6, 1, 7, 7}, new Integer[]{7, 1, 6, 2, 3});

            quarterRound1.setWinner(teamA);
            quarterRound1.setTeamA(teamA);
            quarterRound1.setTeamB(teamB);
            quarterRound1.setDuration(2);

            matchRepository.save(quarterRound1);

            // QUARTER ROUND 2
            Match quarterRound2 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.QUART_FINAL).get(1);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Jessica", "Pegula").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Maria", "Sakkari").get(), null);

            affectTeamsToMatch(quarterRound2, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(quarterRound2, scoreRepository, new Integer[]{3, 6, 1, 7, 7}, new Integer[]{7, 1, 6, 2, 3});

            quarterRound2.setWinner(teamA);
            quarterRound2.setTeamA(teamA);
            quarterRound2.setTeamB(teamB);
            quarterRound2.setDuration(10);

            matchRepository.save(quarterRound2);

            // QUARTER ROUND 3
            Match quarterRound3 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.QUART_FINAL).get(2);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Coco", "Gauff").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Daria", "Kasatkina").get(), null);

            affectTeamsToMatch(quarterRound3, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(quarterRound3, scoreRepository, new Integer[]{3, 6, 1, 7, 7}, new Integer[]{7, 1, 6, 2, 3});

            quarterRound3.setWinner(teamA);
            quarterRound3.setTeamA(teamA);
            quarterRound3.setTeamB(teamB);
            quarterRound3.setDuration(1);

            matchRepository.save(quarterRound3);

            // QUARTER ROUND 4
            Match quarterRound4 = matchRepository.findByTournamentIdAndTypeAndRound(tournamentRepository.findByYear(2022).get().getId(), EType.SIMPLE_WOMEN, ERound.QUART_FINAL).get(3);
            teamA = new Team(personRepository.findByFirstnameAndLastname("Caroline", "Garcia").get(), null);
            teamB = new Team(personRepository.findByFirstnameAndLastname("Aryna", "Sabalenka").get(), null);

            affectTeamsToMatch(quarterRound4, teamA, teamB, teamRepository, matchRepository);
            affectsScoresToMatch(quarterRound4, scoreRepository, new Integer[]{2, 7, 3, 7, 6}, new Integer[]{6, 3, 7, 4, 1});

            quarterRound4.setWinner(teamA);
            quarterRound4.setTeamA(teamA);
            quarterRound4.setTeamB(teamB);
            quarterRound4.setDuration(7);

            matchRepository.save(quarterRound4);
        };
    }

}
