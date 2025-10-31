package com.example.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;
import com.google.adk.web.AdkWebServer;

import java.util.Map;

public class StockTicker {

    private static final String STOCK_PRICE = "stock_price";
    private static final String ERROR = "error";

        public static void main(String[] args) {
            AdkWebServer.start(
                    LlmAgent.builder()
                            .name("stock_agent")
                            .instruction("""
                    You are a stock exchange ticker expert.
                    When asked about the stock price of a company,
                    use the `lookup_stock_ticker` tool to find the information.
                    """)
                            .model("gemini-2.5-flash")
                            .tools(FunctionTool.create(StockTicker.class, "lookupStockTicker"))
                            .build()
            );
        }

    @Annotations.Schema(
            name = "lookup_stock_ticker",
            description = "Lookup stock price for a given company or ticker"
    )
    public static Map<String, String> lookupStockTicker(
            @Annotations.Schema(name = "company_name_or_stock_ticker", description = "The company name or stock ticker")
            String ticker) {
            if (ticker == null || ticker.isEmpty()) {
                return Map.of(ERROR, "Impossible to retrieve stock price, no ticker provided");
            }
            if (ticker.equals("TEST_COMP")) {
                return Map.of(ERROR, "Impossible to retrieve stock price, no data for TEST_COMP");
            }
            return Map.of(STOCK_PRICE, "123");
    }
}
