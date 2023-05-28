
package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class UrlSlack {
    private String url;

    public UrlSlack(String url) {
        this.url = url;
    }

    public UrlSlack() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("""
                             %s
                             """, url);
    }
    
    
    
}
