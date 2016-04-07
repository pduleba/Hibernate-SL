package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.Terminal;

public interface TerminalService {
	
	Terminal getTerminalById(long id);

	void addTerminal(Terminal terminal);

	void updateTerminal(Terminal terminal);

	void deleteTerminal(Terminal terminal);

	List<Terminal> getAllTerminals();
	
	List<Terminal> getAllAvailableTerminals();
}
