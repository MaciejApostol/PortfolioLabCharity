package pl.coderslab.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Deleted;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer sumAllBagsQuantity();

    @Query("SELECT SUM(d.quantity) FROM Donation d WHERE d.user=?1")
    Integer sumAllBagsQuantity(User user);

    long countAllByUser(User user);

    Optional<Donation> findByIdAndDeleted(Long id, Deleted deleted);

    @Query("SELECT d FROM Donation d WHERE d.user = ?1 AND d.deleted = 0")
    List<Donation> findAllByUser(User user, Sort sort);

    List<Donation> findAllByUserAndDeleted(User user, Deleted deleted, Sort sort);
}
