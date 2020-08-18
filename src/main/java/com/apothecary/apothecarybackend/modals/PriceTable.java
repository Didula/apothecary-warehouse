package com.apothecary.apothecarybackend.modals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceTable implements Serializable {

    private String code;
    private List<PriceTableRow> tableRowList;
}
