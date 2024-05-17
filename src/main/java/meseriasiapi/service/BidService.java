package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Bid;
import meseriasiapi.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.*;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid findById(UUID bidId) {
        Optional<Bid> optionalBid = bidRepository.findById(bidId);
        if (optionalBid.isEmpty()) {
            throw new EntityNotFoundException(THERE_BID_FOUND_WITH_THIS_ID + bidId);
        }
        return optionalBid.get();
    }

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public String deleteBid(UUID bidId) {
        Optional<Bid> optionalBid = bidRepository.findById(bidId);
        if (optionalBid.isPresent()) {
            bidRepository.delete(optionalBid.get());
            return BID_WAS_SUCCESFULLY_DELETED;
        } else {
            throw new EntityNotFoundException(NO_BID_WITH_THIS_ID_FOUND);
        }
    }

    public List<Bid> getAllBidsForProject(UUID projectId) {
        List<Bid> bids=bidRepository.findAll();
        List<Bid> bidsForProject = new ArrayList<>();
        for(Bid bid: bids){
            if(bid.getProject().getId().equals(projectId)){
                bidsForProject.add(bid);
            }
        }
        return bidsForProject;

    }
}
