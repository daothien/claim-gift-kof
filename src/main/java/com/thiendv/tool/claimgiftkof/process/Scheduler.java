package com.thiendv.tool.claimgiftkof.process;

import com.thiendv.tool.claimgiftkof.constants.Constants;
import com.thiendv.tool.claimgiftkof.model.Server;
import com.thiendv.tool.claimgiftkof.service.ClaimService;
import com.thiendv.tool.claimgiftkof.service.GetClientKey;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Scheduler {
    @Autowired
    ClaimService claimService;
    @Autowired
    GetClientKey getClientKey;

    @PostConstruct
    public void runTask() {
        Set<String> listIngame = new HashSet<>();
        listIngame.add("253230_21");
        listIngame.add("251514_21");
        listIngame.add("272058_21");
        listIngame.add("7119_21");
        listIngame.add("272909_21");
        listIngame.add("202448_21");
        listIngame.add("145428_21");
        listIngame.add("251875_21");
        listIngame.add("280884_21");
        listIngame.add("275788_21");

        List<Server> liServer = new ArrayList<>();
        getClientKey.setClientKey();
        liServer.add(new Server("snkvn", "22", "pm_snkvn_1588063604339", Constants.CLIENT_KEY_VN));
        liServer.add(new Server("snkindo", "24", "pm_snkindo_1588063604339", Constants.CLIENT_KEY_INDO));
        liServer.add(new Server("snkth", "23", "pm_snkth_1588063604339", Constants.CLIENT_KEY_THAILAND));
        liServer.add(new Server("snkph", "32", "pm_snkph_1588063604339", Constants.CLIENT_KEY_PHILIP));

        for (String ingame : listIngame) {
            System.out.println(ingame);
            for (Server server : liServer) {
                try {
                    String serverID = ingame.split("_")[1];
                    claimService.claim(ingame, server.getId(), server.getClientKey(), server.getPromotionID(), server.getGiftID(), serverID);
                } catch (Exception e) {
                    System.out.println("co loi");
                    continue;
                }
            }
            System.out.println("-------------------------------------------");

        }
    }
}
