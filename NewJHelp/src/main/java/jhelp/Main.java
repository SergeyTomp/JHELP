package jhelp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        GUIFrame bean = ctx.getBean(GUIFrame.class);
        bean.pack();
        bean.setVisible(true);
    }

}
