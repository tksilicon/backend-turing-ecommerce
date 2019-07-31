/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.util.List;

import com.turing.ecommerce.model.Product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thankgodukachukwu
 *
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetAllDTO {
	@ApiModelProperty(example="20")
	private String count;
	@ApiModelProperty(dataType="List", value = "rows")
	private List<ProductAll> rows;

}
