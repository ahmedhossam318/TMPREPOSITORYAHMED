package mts.ftth.vc4.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="FTTH_CROSSCONNECT", schema = "SO2")
public class FTTHCrossConnection {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "SO2.FTTH_CROSSCONNECT_SEQ", allocationSize=1)
	@Column(name="QUEUE_ID")
	private Long QueueId;
	
	@Column(name="FCC_CODE")
    private String FccCode;
	
	@Column(name="GPON_HOST")
    private String GponHost;
	
	@Column(name="POP_ID")
    private Long PopId;
	
	@Column(name="POP_NAME")
    private String PopName;
	
	@Column(name="CABIN_CODE")
    private String CabinCode;
	
	@Column(name="SHELF")
    private Long Shelf;
	
	@Column(name="CARD")
    private Long Card;
	
	@Column(name="GPON_PORT")
    private Long GponPort;
	
	@Column(name="MMR_ACTIVE_ODF")
    private String MmrActiveOdf;
	
	@Column(name="MMR_ACTIVE_PORT")
    private Long MmrActivePort;
	
	@Column(name="MMR_PASSIVE_ODF")
    private String MmrPassiveOdf;
	
	@Column(name="MMR_PASSIVE_PORT")
    private Long MmrPassivePort;
	
	@Column(name="PASSIVE__CABIN")
    private String PassiveCabin;
	
	@Column(name="BOX_NUMBER")
    private String BoxNumber;
	
	@Column(name="BOX_CAPACITY")
    private Long BoxCapacity;
	
	@Column(name="CABIN_SPLITTER_ID_")
    private String CabinSplitterId;
	
	@Column(name="CABIN_SPLITTER_PORT_#")
    private Long CabinSplitterPort;
	
	@Column(name="CABIN_SPLITTER_CAPACITY")
    private String CabinSplitterCapacity;
	
	@Column(name="BULIDING")
    private String Building;
	
	@Column(name="COPPER_EXCH")
    private String CopperExch;
	
	@Column(name="COPPER_CABINET")
    private String CopperCabinet;
	
	@Column(name="COPPER_BOX")
    private String CopperBox;
	
	@Column(name="STATUS")
    private String Status;
	
	@Column(name="FILE_ID")
    private Long FileId;
	
	@Column(name="FAIL_REASON")
    private String FailReason;
	
	@Column(name="UPLOAD_DATE")
	@JsonFormat(timezone = "GMT+02:00")
    private Date UploadDate;
	
	@Column(name="USER_NAME")
    private String UserName;
	
	@Column(name="IP")
    private String Ip;
	
	@Column(name="UPDATE_DATE")
	@JsonFormat(timezone = "GMT+02:00")
    private Date UpdateDate;
}
