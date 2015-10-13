package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Notify;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Repository
public interface NotifyRepo extends JpaRepository<Notify, Integer> {

    @Query("select n from Notify n where receiver.id = :receiverId")
    public List<Notify> findByReceiverId(@Param("receiverId") final int receiverId);
}
