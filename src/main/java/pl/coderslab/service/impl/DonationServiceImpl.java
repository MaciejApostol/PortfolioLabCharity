package pl.coderslab.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.*;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.service.DonationService;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public int count(User user) {
        long count;
        if (user == null) {
            count = donationRepository.count();
        } else {
            count = donationRepository.countAllByUser(user);
        }
        return (int) count;
    }

    @Override
    public int sumBagsQuantity(User user) {
        Integer sumBagsQuantity;
        if (user == null) {
            sumBagsQuantity = donationRepository.sumAllBagsQuantity();
        } else {
            sumBagsQuantity = donationRepository.sumAllBagsQuantity(user);
        }
        if (sumBagsQuantity == null) {
            return 0;
        }
        return sumBagsQuantity;
    }

    @Override
    public void save(Donation donation) {
        donation.setStatus(Status.NOT_TAKEN);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAllByUser(User user) {
        Sort sort = Sort.by("status").ascending()
                .and(Sort.by("pickUpDate").ascending())
                .and(Sort.by("pickUpTime").ascending())
                .and(Sort.by("orderDate").ascending());
        return donationRepository.findAllByUser(user, sort);
    }

    @Override
    public Donation findById(Long id) {
        return donationRepository.findByIdAndDeleted(id, Deleted.AVAILABLE)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Donation donation = findById(id);
        donation.setDeleted(Deleted.DELETED);
        donationRepository.save(donation);
    }
}
