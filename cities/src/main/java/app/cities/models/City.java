package app.cities.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City
{
    private @Id @GeneratedValue Long id;
    private String name;
    private int medianPrice;
    private int index;

    public City()
    {
    }

    public City(String name, int medianPrice, int index)
    {
        this.name = name;
        this.medianPrice = medianPrice;
        this.index = index;
    }
}
