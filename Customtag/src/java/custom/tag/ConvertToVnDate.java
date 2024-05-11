/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package custom.tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.JspFragment;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ConvertToVnDate extends SimpleTagSupport {

    private String value;

    public void setValue(String value){
        this.value= value;
    }
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            DateFormat inputFormat =new SimpleDateFormat("MM-dd-yyyy");
            Date date = inputFormat.parse(value);
            
            DateFormat outputFormat = new SimpleDateFormat("dd', thang' MM', nam' yyyy");
            String vnDate= outputFormat.format(date);
            
            out.println("Ngay "+vnDate);

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ConvertToVnDate tag", ex);
        } catch (ParseException ex) {
            Logger.getLogger(ConvertToVnDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
