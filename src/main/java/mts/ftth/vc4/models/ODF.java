package mts.ftth.vc4.models;

public class ODF {
    private String id;
	private String capacity;
	private String odfcat;
	private String odftype;
	private String odfname;
	private String rowVersion;
	private String errors;
	private String hasErrors;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getOdfcat() {
		return odfcat;
	}
	public void setOdfcat(String odfcat) {
		this.odfcat = odfcat;
	}
	public String getOdftype() {
		return odftype;
	}
	public void setOdftype(String odftype) {
		this.odftype = odftype;
	}
	public String getOdfname() {
		return odfname;
	}
	public void setOdfname(String odfname) {
		this.odfname = odfname;
	}
	public String getRowVersion() {
		return rowVersion;
	}
	public void setRowVersion(String rowVersion) {
		this.rowVersion = rowVersion;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(String hasErrors) {
		this.hasErrors = hasErrors;
	}
	
}
