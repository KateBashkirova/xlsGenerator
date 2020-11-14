package xlsBuilders.styles;

import org.apache.poi.ss.usermodel.IndexedColors;

public enum Colors {
    BLACK {
        public short getColor() {
            return IndexedColors.BLACK.getIndex();
        }
    },
    AQUA {
        public short getColor() {
            return IndexedColors.AQUA.getIndex();
        }
    };
    public abstract short getColor();
}
