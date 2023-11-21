package br.com.api.gerenciamentodepedidos.validation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.gerenciamentodepedidos.DTO.ClienteNewDTO;
import br.com.api.gerenciamentodepedidos.controllers.exceptions.FieldMessage;
import br.com.api.gerenciamentodepedidos.enums.TipoCliente;
import br.com.api.gerenciamentodepedidos.models.Cliente;
import br.com.api.gerenciamentodepedidos.repositories.ClienteRepository;
import br.com.api.gerenciamentodepedidos.validation.ClienteInsert;
import br.com.api.gerenciamentodepedidos.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO obj, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (obj.getTipo() == null) {
			list.add(new FieldMessage("tipo", "Valor nulo"));
		} else {
			
			if (!TipoCliente.codExist(obj.getTipo()))
			list.add(new FieldMessage("tipo", "Não existe esse tipo de cliente"));

			if (obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(obj.getCpfOuCnpj()))
				list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

			if (obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(obj.getCpfOuCnpj()))
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

		}

		Cliente aux = clienteRepository.findByEmail(obj.getEmail());
		if (aux != null)
			list.add(new FieldMessage("email", "Email já existente"));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
