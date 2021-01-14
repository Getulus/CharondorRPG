package com.getulus.charondor.gamebody.service;


import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CombatLogList {

    private List<CombatLogTemplate> combatLog;

}
