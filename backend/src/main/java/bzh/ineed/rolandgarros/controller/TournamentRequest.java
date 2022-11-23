package bzh.ineed.rolandgarros.controller;
import bzh.ineed.rolandgarros.model.EType;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TournamentRequest {
    @NotNull
    private Integer year;

    @NotNull
    private List<String> types;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
