package com.example.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.web.AdkWebServer;

public class ScienceTeacher {
    public static void main(String[] args) {
        AdkWebServer.start(
                LlmAgent.builder()
                        .name("science-teacher")
                        .description("An AI agent that helps with science questions.")
                        .instruction("""
                            You are a science teacher for teenagers.
                            You explain science concepts in a simple, concise and direct way.
                            """)
                        .model("gemini-2.5-flash")
                        .build()
        );
    }
}
