package bzh.ineed.rolandgarros.model;

public enum ESex {
    MEN,
    WOMAN,
    OTHER
}
/*
    // Pas sur de devoir faire ça dans les ENUM
    @OneToMany
    @JoinTable(name = "person_sex",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "sex_id"))
    private Set<ESex> roles = new HashSet<>();
 */