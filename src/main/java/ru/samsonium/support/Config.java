package ru.samsonium.support;

public class Config {
    private static Config _instance;

    /** Should interpreter show debug output from the lexer */
    private boolean dLexer;

    /** Should interpreter show debug output from the parser */
    private boolean dParser;

    public static Config getInstance() {
        if (_instance == null)
            _instance = new Config(false, false);
        return _instance;
    }

    /** Create config with initial values */
    private Config(boolean dLexer, boolean dParser) {
        this.dLexer = dLexer;
        this.dParser = dParser;
    }

    /** Debug lexer value getter */
    public boolean isDebugLexer() {
        return dLexer;
    }

    /** Debug lexer value setter */
    public void setDebugLexer(boolean dLexer) {
        this.dLexer = dLexer;
    }

    /** Debug parser value getter */
    public boolean isDebugParser() {
        return dParser;
    }

    /** Debug parser value setter */    
    public void setDebugParser(boolean dParser) {
        this.dParser = dParser;
    }
}
