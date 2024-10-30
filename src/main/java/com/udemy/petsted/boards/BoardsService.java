package com.udemy.petsted.boards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoardsService {
    @Autowired
    public BoardsRepository boardsRepository;
    @Autowired
    public MissingPetsRepository missingPetsRepository;
    @Autowired
    public PetQnaRepository petQnaRepository;
    @Autowired
    public PetCareReopsitory petCareReopsitory;


    public Boards create(User user, String content, String hashtag, Integer post_type ){
        Boards b = new Boards();

        b.setUser(user);
        b.setContent(content);
        b.setHashtag(hashtag);
        b.setPost_type(post_type);
        b.setCreated_at(LocalDate.now());

        Boards saveBoard = this.boardsRepository.save(b);

        return saveBoard;
    }

    public void createMissingPets(User user, String content, String hashtag, Integer post_type,
                                String region, LocalDateTime from, LocalDateTime to){

        Boards b = create(user, content, hashtag, post_type);

        MissingPets mp = new MissingPets();

        mp.setBoard(b);
        mp.setRegion(region);
        mp.setFrom(from);
        mp.setTo(to);

        this.missingPetsRepository.save(mp);
    }

    public void createPetQna(User user, String content, String hashtag, Integer post_type,
                            String title){
        Boards b = create(user, content, hashtag, post_type);
        PetQna pq = new PetQna();
        pq.setBoard(b);
        pq.setTitle(title);

        this.petQnaRepository.save(pq);
    }

    public void createPetcare(User user, String content, String hashtag, Integer post_type,
                            boolean need_care, Integer care_role, LocalDate scheduled_date){

        Boards b = create(user, content, hashtag, post_type);

        PetCare pc = new PetCare();

        pc.setBoard(b);
        pc.setNeedCare(need_care);
        pc.setCare_role(care_role);
        pc.setScheduled_date(scheduled_date);

        this.petCareReopsitory.save(pc);
    }



    public void modify(Boards b, String content, String hashtag){
        b.setContent(content);
        b.setHashtag(hashtag);
        b.setUpdated_at(LocalDate.now());

        this.boardsRepository.save(b);
    }

    public void modifyMissingPets(MissingPets mp, String content, String hashtag,
                                String region, LocalDateTime from, LocalDateTime to){
        Boards b = mp.getBoard();
        modify(b,content,hashtag);
        mp.setRegion(region);
        mp.setFrom(from);
        mp.setTo(to);
        this.missingPetsRepository.save(mp);
    }

    public void modifyPetCare(PetCare pc, String content, String hashtag,
                                boolean needsCare, Integer careRole, LocalDate scheduled_date){
        Boards b = pc.getBoard();
        modify(b,content,hashtag);

        pc.setNeedCare(needsCare);
        pc.setCare_role(careRole);
        pc.setScheduled_date(scheduled_date);

        this.petCareReopsitory.save(pc);

    }
    public void delete(Boards b){
        this.boardsRepository.delete(b);
    }

    public Boards getBoard(Integer board_id){
        Optional<Boards> board = this.boardsRepository.findById(board_id);
        if(board.isPresent()){
            return board.get();
        }else {
            throw new HibernateException("board not found by ID");
        }
    }

    public MissingPets getMissingPets(Integer missing_pets_id) {
        Optional<MissingPets> board = this.missingPetsRepository.findById(missing_pets_id);
        if(board.isPresent()){
            return board.get();
        }else {
            throw new HibernateException("board not found by ID");
        }
    }

    public void vote(Boards board, User user){
        board.getVoter().add(user);
    }

}
