package pl.coderslab.service;

import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.User;

import java.util.List;

public interface DonationService {
    Donation findById(Long id);

    List<Donation> findAllByUser(User user);

    void save(Donation donation);

    void deleteById(Long id);

    int count(User user);

    int sumBagsQuantity(User user);

}
