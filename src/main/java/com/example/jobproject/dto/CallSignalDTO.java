package com.example.jobproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CallSignalDTO {
    private String senderEmail;
    private String receiverEmail;
    private String signalType;
}
