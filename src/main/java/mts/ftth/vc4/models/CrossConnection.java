package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class CrossConnection {
    private String exchcode;
    private String cabinetid;
    private String cabinetno;
    private String cabinetname;
    private String popid;
    private String popname;
    private String splitterid;
    private String splitterno;
    private String id;
    private String splittername;
    private String splittercapacity;
    private String splittermodel;
    private String splitterport;
    private String gponid;
    private String shelfid;
    private String cardid;
    private String gponport;
    private String mmraodf;
    private String mmraport;
    private String mmrpodf;
    private String mmrpport;
    private String boxid;
    private String boxsiteid;
    private String boxno;
    private String boxcapacity;
    private String boxaddress;
    private String rowVersion;
    private String errors;
    private String hasErrors;
}
