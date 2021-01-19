package com.getulus.charondor.gamebody.service.Adventures;


import com.getulus.charondor.gamebody.model.advantures.Adventure;
import com.getulus.charondor.gamebody.repository.AdventureRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdventureList {

    @Autowired
    AdventureRepository adventureRepository;

    private List<Adventure> adventureList;
    private Adventure CurrentAdventure;


}
