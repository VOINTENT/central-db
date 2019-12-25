package ru.kpfu.icmit.client4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.client4.model.Nomenclature;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NomenclatureRepository extends GenericRepository<Nomenclature> {

    @Query("select n from Nomenclature n where uid = :uid ")
    Optional<Nomenclature> getByUid(@Param("uid") UUID uid);

    @Query("select n from Nomenclature n where modifyDate >= :dateAfter ")
    List<Nomenclature> getAllByModifyDateAfter(@Param("dateAfter") Timestamp dateAfter);

    @Transactional
    Nomenclature save(Nomenclature nomenclature);
}
