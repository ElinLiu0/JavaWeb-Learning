package chap3.helloapp.src;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
public class HelloTag extends TagSupport{ // 继承TagSupport类
    /*当JSP解析器遇到hello标签的结束标志时，调用此方法*/
    public int doEndTag() throws JspException{
        try{
            pageContext.getOut().write("Hello");
        }catch(Exception e){
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
