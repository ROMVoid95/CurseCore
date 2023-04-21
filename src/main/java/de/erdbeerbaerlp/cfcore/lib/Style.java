package de.erdbeerbaerlp.cfcore.lib;

import org.apache.commons.lang3.builder.ToStringStyle;

public final class Style
{
    public static final ToStringStyle JsonStyle = new GsonToStringStyle();
    
    public static class GsonToStringStyle extends ToStringStyle
    {
        private static final long serialVersionUID = 1L;

        public GsonToStringStyle()
        {
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
            this.setContentStart("[");
            this.setFieldSeparator(System.lineSeparator() + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(System.lineSeparator() + "]");
        }
        
        /**
         * <p>
         * Ensure {@code Singleton} after serialization.
         * </p>
         *
         * @return the singleton
         */
        private Object readResolve() {
            return MULTI_LINE_STYLE;
        }
    }

}
