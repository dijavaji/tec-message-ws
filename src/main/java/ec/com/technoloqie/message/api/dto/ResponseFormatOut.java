package ec.com.technoloqie.message.api.dto;

public class ResponseFormatOut {

	// @ApiModelProperty("Indica si la ejecucion del servicio en la capa se ejecuto
	// sin errores (pudieran ocurrir errores en el backend, pero este campo es para
	// indicar el estatus de ejecucion en la capa).")
	private Boolean success;

	// @ApiModelProperty("Mensaje de o fracaso de la ejecucion del servicio en la
	// capa.")
	private String message;

	// @ApiModelProperty("El campo data es para asignar los datos que se recuperan
	// del servicio. En caso de error es NULL.")
	private Object data;

	// @ApiModelProperty("Indica el codigo y detalle del error que haya ocurrido en
	// el backend. Si no hay error es NULL.")
	private Error error;

	// @ApiModelProperty(value = "Campo para la trazabilidad de la ejecucion del
	// servicio entre la capa y los backends, es un valor UUID.", example =
	// "fcdf721f-e521-443b-8130-a59742c4bc19")
	private String traceid;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getTraceid() {
		return traceid;
	}

	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}

	// @ApiModel(description = "Indica el detalle del error en el backend.")
	public static class Error {
		// @ApiModelProperty("Codigo de error en el backend.")
		private String code;

		// @ApiModelProperty("Detalle del error en el backend.")
		private String detail;

		public void setCode(String code) {
			this.code = code;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public Error() {
		}

		// @ConstructorProperties({"code", "detail"})
		public Error(String code, String detail) {
			this.code = code;
			this.detail = detail;
		}

		public String getCode() {
			return this.code;
		}

		public String getDetail() {
			return this.detail;
		}
	}
}
