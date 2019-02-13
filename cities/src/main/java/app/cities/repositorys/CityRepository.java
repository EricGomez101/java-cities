package app.cities.repositorys;

import app.cities.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>
{
}
