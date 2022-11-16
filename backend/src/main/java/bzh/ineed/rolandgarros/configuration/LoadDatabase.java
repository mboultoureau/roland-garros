package bzh.ineed.rolandgarros.configuration;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.EHand;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            // USERS



            // PERSONS
            log.info("[PERSON]: Delete all");
            repository.deleteAll();

            // Womans
            Person player1 = new Person("Iga", "Swiatek");
            player1.setGender(EGender.FEMALE);
            player1.setBirthDate(LocalDate.parse("2001-05-31"));
            player1.setHeight(175);
            player1.setBirthPlace("Varsosie, Pologne");
            player1.setIsCoach(false);
            player1.setIsPlayer(true);
            player1.setHand(EHand.RIGHT);
            player1.setPicture("https://images.prismic.io/fft-rg-site/1a1fe64f-69f1-429d-a6d2-1be8f110be47_20200308_PJ_SwiatekIga_US.png?auto=compress,format&rect=0,0,1080,1080&w=200&h=200");
            player1.setRanking(1);

            Person player2 = new Person("Ons", "Jabeur");
            player2.setGender(EGender.FEMALE);
            player2.setBirthDate(LocalDate.parse("1994-08-28"));
            player2.setBirthPlace("Ksar Hellal, Tunisie");
            player2.setHeight(167);
            player2.setHand(EHand.RIGHT);
            player2.setIsCoach(false);
            player2.setIsPlayer(true);
            player2.setPicture("https://images.prismic.io/fft-rg-site/0942c177-cde4-4f29-8c11-5ec5a19df99e_20220427_PJ_JabeurOns_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player2.setRanking(2);

            Person player3 = new Person("Jessica", "Pegula");
            player3.setGender(EGender.FEMALE);
            player3.setBirthPlace("Buffalo, New York, Etats-Unis");
            player3.setBirthDate(LocalDate.parse("1994-02-24"));
            player3.setHeight(170);
            player3.setHand(EHand.RIGHT);
            player3.setIsCoach(false);
            player3.setIsPlayer(true);
            player3.setPicture("https://images.prismic.io/fft-rg-site/39fed296-454c-445b-8988-0390211d9626_20220427_PJ_PegulaJessica_US.PNG?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player3.setRanking(3);

            Person player4 = new Person("Caroline", "Garcia");
            player4.setGender(EGender.FEMALE);
            player4.setBirthDate(LocalDate.parse("1993-10-16"));
            player4.setBirthPlace("Saint-Germain-en-Laye, France");
            player4.setHeight(177);
            player4.setHand(EHand.RIGHT);
            player4.setIsCoach(false);
            player4.setIsPlayer(true);
            player4.setPicture("https://images.prismic.io/fft-rg-site/3dbbc6e2-ed47-4a23-af86-495a4c9e2ca8_20180524_PJ_GarciaCaroline_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player4.setRanking(4);

            Person player5 = new Person("Aryna", "Sabalenka");
            player5.setGender(EGender.FEMALE);
            player5.setBirthPlace("Minsk, Biélorussie");
            player5.setBirthDate(LocalDate.parse("1998-05-05"));
            player5.setHeight(182);
            player5.setHand(EHand.RIGHT);
            player5.setIsCoach(false);
            player5.setIsPlayer(false);
            player5.setPicture("https://images.prismic.io/fft-rg-site/f38acf23-789e-4d62-b39d-0cf22797e1a3_20190302_PJ_SabalenkaAryna_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player5.setRanking(5);

            Person player6 = new Person("Maria", "Sakkari");
            player6.setGender(EGender.FEMALE);
            player6.setBirthPlace("Athènes, Grèce");
            player6.setBirthDate(LocalDate.parse("1995-08-25"));
            player6.setHeight(172);
            player6.setHand(EHand.RIGHT);
            player6.setIsCoach(false);
            player6.setIsPlayer(true);
            player6.setPicture("https://images.prismic.io/fft-rg-site/f2be46ed-3c69-4f45-bd34-f4c2ea568929_20200311_PJ_SakkariMaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player6.setRanking(6);

            Person player7 = new Person("Coco", "Gauff");
            player7.setGender(EGender.FEMALE);
            player7.setBirthDate(LocalDate.parse("2004-03-13"));
            player7.setBirthPlace("Delray Beach, Fl, États-Unis");
            player7.setHeight(175);
            player7.setHand(EHand.RIGHT);
            player7.setIsCoach(false);
            player7.setIsPlayer(true);
            player7.setPicture("https://images.prismic.io/fft-rg-site/2faad022-abf7-48e4-842a-34534366b5fd_20200310_PJ_GauffCory_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player7.setRanking(7);

            Person player8 = new Person("Daria", "Kasatkina");
            player8.setGender(EGender.FEMALE);
            player8.setBirthPlace("Togliatti, Russie");
            player8.setBirthDate(LocalDate.parse("1997-05-07"));
            player8.setHeight(170);
            player8.setIsCoach(false);
            player8.setIsPlayer(true);
            player8.setPicture("https://images.prismic.io/fft-rg-site/45777401-3e3b-459c-98d9-dc54f00547e9_20190306_PJ_KasatkinaDaria_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player8.setRanking(8);

            // Mans
            Person player9 = new Person("Carlos", "Alcaraz");
            player9.setGender(EGender.MALE);
            player9.setHand(EHand.RIGHT);
            player9.setBirthPlace("El Palmar, Murcia, Espagne");
            player9.setBirthDate(LocalDate.parse("2003-05-05"));
            player9.setHeight(182);
            player9.setWeight(74);
            player9.setIsCoach(false);
            player9.setIsPlayer(true);
            player9.setEarlyCareer(LocalDate.parse("2018-01-01"));
            player9.setPicture("https://images.prismic.io/fft-rg-site/6ca816f6-ef3f-4633-9a97-155651ca4857_20220308_PJ_AlcarazCarlos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player9.setRanking(1);

            Person player10 = new Person("Rafael", "Nadal");
            player10.setGender(EGender.MALE);
            player10.setBirthPlace("Manacor, Espagne");
            player10.setBirthDate(LocalDate.parse("1986-06-03"));
            player10.setEarlyCareer(LocalDate.parse("2001-01-01"));
            player10.setHand(EHand.LEFT);
            player10.setHeight(185);
            player10.setWeight(84);
            player10.setIsCoach(false);
            player10.setIsPlayer(true);
            player10.setPicture("https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400");
            player10.setRanking(2);

            log.info("[PERSON] Preloading " + repository.save(player1));
            log.info("[PERSON] Preloading " + repository.save(player2));
            log.info("[PERSON] Preloading " + repository.save(player3));
            log.info("[PERSON] Preloading " + repository.save(player4));
            log.info("[PERSON] Preloading " + repository.save(player5));
            log.info("[PERSON] Preloading " + repository.save(player6));
            log.info("[PERSON] Preloading " + repository.save(player7));
            log.info("[PERSON] Preloading " + repository.save(player8));
            log.info("[PERSON] Preloading " + repository.save(player9));
            log.info("[PERSON] Preloading " + repository.save(player10));
        };
    }

}
