<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<%@ attribute name="etiqueta"%>
<%@ attribute name="id"%>
<%@ attribute name="tipo"%>
<%@ attribute name="tiposFichero"%>
<%@ attribute name="decimales"%>
<%@ attribute name="valor"%>

<div class="row mb-3">
	<c:choose>
		<c:when test="${tipo == 'submit'}">
			<div class="offset-sm-2 col-sm">
				<button type="submit" class="btn btn-primary">${etiqueta}</button>
			</div>
		</c:when>
		<c:otherwise>
			<label for="${id}" class="col-sm-2 col-form-label">${etiqueta}</label>
			<div class="col-sm">
				<c:choose>
					<c:when test="${tipo == 'textarea'}">
						<textarea class="form-control" id="${id}" name="${id}" rows="5">${valor}</textarea>
					</c:when>
					<c:otherwise>
						<input type="${tipo == null ? 'text' : tipo}" class="form-control"
							id="${id}" name="${id}" value="${valor}"
							step="${decimales != null ? (1.0 / (Math.pow(10, decimales))) : ''}"
							accept="${tiposFichero != null ? tiposFichero: ''}">
					</c:otherwise>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</div>