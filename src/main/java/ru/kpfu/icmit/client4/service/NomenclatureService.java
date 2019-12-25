package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Nomenclature;
import ru.kpfu.icmit.client4.repository.NomenclatureRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NomenclatureService extends CrudServiceImpl<Nomenclature, NomenclatureRepository> {

    public NomenclatureService(NomenclatureRepository repository) {
        super(repository);
    }

    public Optional<Nomenclature> getByUid(UUID uid){
        return genericRepository.getByUid(uid);
    }

    public List<Nomenclature> getAllByModifyDateAfter(Timestamp dateAfter){
        return genericRepository.getAllByModifyDateAfter(dateAfter);
    }

}
