package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.npos.NPO;
import com.sitblueprint.admin.repository.users.NPORepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NPOServiceImpl implements NPOService {

    NPORepository npoRepository;

    public NPOServiceImpl(NPORepository npoRepository) {
        this.npoRepository = npoRepository;
    }

    @Override
    public List<NPO> getAllNPOs() {
        return npoRepository.findAll();
    }

    @Override
    public NPO getNPOById(Long id) {
        return npoRepository.findById(id).orElse(null);
    }

    @Override
    public NPO createNPO(NPO npo) {
        //npo.setDateOfRecruitment(LocalDate.now());
        return npoRepository.save(npo);
    }

    @Override
    public NPO updateNPO(NPO npo) {
        return npoRepository.saveAndFlush(npo);
    }

    @Override
    public void deleteNPOById(Long id) {
        npoRepository.deleteById(id);
    }
}
