package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.Terminal;
import com.pduleba.spring.dao.TerminalDao;

@Service
@Transactional
public class TerminalServiceImpl implements TerminalService{

	@Autowired
	TerminalDao dao;
	
	@Override
	public Terminal getTerminalById(long id) {
		return dao.getTerminalById(id);
	}

	@Override
	public void addTerminal(Terminal terminal) {
		dao.addTerminal(terminal);
	}

	@Override
	public void updateTerminal(Terminal terminal) {
		Terminal terminalToUpdate = getTerminalById(terminal.getIdTerminal());
		terminalToUpdate.setAvailable(terminal.getAvailable());
		terminalToUpdate.setType(terminal.getType());
		terminalToUpdate.setDescription(terminal.getDescription());
		dao.updateTerminal(terminalToUpdate);
		
	}

	@Override
	public void deleteTerminal(Terminal terminal) {
		dao.deleteTerminal(terminal);
	}

	@Override
	public List<Terminal> getAllTerminals() {
		return dao.getAllTerminals();
	}

	@Override
	public List<Terminal> getAllAvailableTerminals() {
		return dao.getAllAvailableTerminals();
	}

}
