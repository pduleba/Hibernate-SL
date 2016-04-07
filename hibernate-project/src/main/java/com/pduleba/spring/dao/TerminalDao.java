package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.Terminal;

public interface TerminalDao {

    void addTerminal(Terminal terminal);
    
    void deleteTerminal(Terminal terminal);
     
    List<Terminal> getAllTerminals();
    
    List<Terminal> getAllAvailableTerminals();
 
    Terminal getTerminalById(long id);
    
    void updateTerminal(Terminal terminal);

	void deleteAllTerminals();
	
}
