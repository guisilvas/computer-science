// Guia_0701 - GATES
// Nome: Guilherme Soares Silva
// Matricula: 863485
module LU (output final_out, input a, input b, input select_op, input select_group);
    wire and_out, nand_out, or_out, nor_out;
    wire group1_out, group2_out;

    and AND1 (and_out, a, b);    
    nand NAND1 (nand_out, a, b); 
    or OR1 (or_out, a, b);       
    nor NOR1 (nor_out, a, b);    

    wire not_select_op;
    wire sa, sb;
    
    not NOT1 (not_select_op, select_op);
    and AND2 (sa, nand_out, not_select_op); 
    and AND3 (sb, and_out, select_op);      
    or OR2 (group1_out, sa, sb);            

    wire sc, sd;
    and AND4 (sc, nor_out, not_select_op);  
    and AND5 (sd, or_out, select_op);       
    or OR3 (group2_out, sc, sd);            
 
    wire not_select_group;
    wire se, sf;
    
    not NOT2 (not_select_group, select_group);
    and AND6 (se, group1_out, not_select_group); 
    and AND7 (sf, group2_out, select_group);     
    or OR4 (final_out, se, sf);                  

endmodule

module test_LU;
reg x;
reg y;
reg select_op;
reg select_group;
wire z;

LU modulo (z, x, y, select_op, select_group);

initial begin : main
    $display("Guia_0701 - Guilherme Soares Silva - 863485");
    x = 1'b0; y = 1'b1; select_op = 1'b0; select_group = 1'b0;
    
    #1 $monitor("%4b %4b %4b %4b | %4b", x, y, select_op, select_group, z);
    
    #1 x = 1'b0; y = 1'b0; select_op = 1'b0; select_group = 1'b0;
    #1 x = 1'b1; y = 1'b0; select_op = 1'b0; select_group = 1'b1;
    #1 x = 1'b1; y = 1'b1; select_op = 1'b1; select_group = 1'b0;
    #1 x = 1'b1; y = 1'b1; select_op = 1'b1; select_group = 1'b1;
end

endmodule

