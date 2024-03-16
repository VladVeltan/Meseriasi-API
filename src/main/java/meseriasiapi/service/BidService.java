package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Bid;
import meseriasiapi.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.THERE_BID_FOUND_WITH_THIS_ID;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid findById(UUID bidId){
        Optional<Bid>optionalBid=bidRepository.findById(bidId);
        if(optionalBid.isEmpty()){
            throw new EntityNotFoundException(THERE_BID_FOUND_WITH_THIS_ID+bidId);
        }
        return optionalBid.get();
    }
    public List<Bid> getAllBids(){
        return bidRepository.findAll();
    }
}
