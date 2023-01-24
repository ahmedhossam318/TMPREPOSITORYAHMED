package mts.ftth.vc4.services;

import java.util.List;

import mts.ftth.vc4.services.apiInterface.SysConfigVC4Service;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.SYS_CONFIG;
import mts.ftth.vc4.repos.SysConfigRepo;

@Service
@RequiredArgsConstructor
public class SysConfigVC4ServiceImpl implements SysConfigVC4Service {

    private final SysConfigRepo sysConfigRepo;

    @Override
    public List<SYS_CONFIG> GetAllSysConfigVc4() {
        return sysConfigRepo.GetAllSysConfig();
    }

}
