package org.apache.poi.xwpf.converter.core.styles.run;

import org.apache.poi.xwpf.converter.core.styles.XWPFStylesDocument;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

/**
 * @Author maoXin
 * @Description
 * @Date 14:40 2022/3/3
 */
public class RunUnderlineValueProvider extends AbstractRunValueProvider<UnderlinePatterns>{

    public static final RunUnderlineValueProvider INSTANCE = new RunUnderlineValueProvider();

    @Override
    public UnderlinePatterns getValue(CTRPr rPr, XWPFStylesDocument stylesDocument) {
        return (rPr != null && rPr.isSetU() && rPr.getU() != null && rPr.getU().getVal() != null ?
                UnderlinePatterns.valueOf(rPr.getU().getVal().intValue()) : null);
    }
}
