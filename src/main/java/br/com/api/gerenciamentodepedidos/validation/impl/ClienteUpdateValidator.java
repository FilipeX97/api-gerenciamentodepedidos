package br.com.api.gerenciamentodepedidos.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.api.gerenciamentodepedidos.DTO.ClienteDTO;
import br.com.api.gerenciamentodepedidos.controllers.exceptions.FieldMessage;
import br.com.api.gerenciamentodepedidos.models.Cliente;
import br.com.api.gerenciamentodepedidos.repositories.ClienteRepository;
import br.com.api.gerenciamentodepedidos.validation.ClienteUpdate;

public class ClienteUpdateValidator  implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}

	@Override
	public boolean isValid(ClienteDTO obj, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = repository.findByEmail(obj.getEmail());
		if(aux != null && !aux.getId().equals(uriId))
			list.add(new FieldMessage("email", "Email já existente"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}