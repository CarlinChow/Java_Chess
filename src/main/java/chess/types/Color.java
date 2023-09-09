package chess.types;

public enum Color {
    BLACK {
        @Override
        public String toString(){
            return "BLACK";
        }
    },
    WHITE {
        @Override
        public String toString(){
            return "WHITE";
        }
    },
}
