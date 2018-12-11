package jhelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import jhelp.repos.DefinitionRepository;
import jhelp.repos.TermRepository;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "jhelp.repos")
@Import(DbConfig.class)
public class JavaConfig {

    @Bean
    @Autowired
    public GUIFrame guiFrame(
            TermRepository termRepository,
            DefinitionRepository definitionRepository,
            DataSource dataSource
    ){
        GUIFrame guiFrame = new GUIFrame();
        guiFrame.setDefinitionRepository(definitionRepository);
        guiFrame.setTermRepository(termRepository);
        guiFrame.setDataSource(dataSource);

        return guiFrame;
    }

}

