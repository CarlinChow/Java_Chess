package chess.logic.types;

public enum GameStatus {
    ACTIVE,
    BLACK_WIN{
        @Override
        public String toString(){
            return "BLACK WINS";
        }
    },
    WHITE_WIN{
        @Override
        public String toString(){
            return "WHITE WINS";
        }
    },
    BLACK_WIN_BY_RESIGNATION{
        @Override
        public String toString(){
            return "BLACK WINS BY RESIGNATION";
        }
    },
    WHITE_WIN_BY_RESIGNATION{
        @Override
        public String toString(){
            return "WHITE WINS BY RESIGNATION";
        }
    },
    STALEMATE{
        @Override
        public String toString(){
            return "STALEMATE";
        }
    }
}
