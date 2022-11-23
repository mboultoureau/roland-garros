package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.BadRequestException;
import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class TournamentController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/tournaments/{id}/players")
    public Page<Person> players(
            @PathVariable Long id,
            @ParameterObject Pageable pageable,
            @RequestParam(defaultValue = "") String type
    ) {
        EType etype = null;
        if (type.equals("")) {
            etype = null;
        } else if (type.equals("SIMPLE_MEN")) {
            etype = EType.SIMPLE_MEN;
        } else if (type.equals("SIMPLE_WOMEN")) {
            etype = EType.SIMPLE_WOMEN;
        } else if (type.equals("DOUBLE_MEN")) {
            etype = EType.DOUBLE_MEN;
        } else if (type.equals("DOUBLE_WOMAN")) {
            etype = EType.DOUBLE_WOMAN;
        } else if (type.equals("MIXED")) {
            etype = EType.MIXED;
        } else {
            throw new BadRequestException("Type " + type + " is not valid");
        }

        if (etype == null) {
            return personRepository.findByTournamentId(id, pageable);
        } else {
            return personRepository.findByTournamentIdAndType(id, etype, pageable);
        }
    };
}
