// Guia_0101.v
module Guia_0101;

    // data define
    reg [31:0] decimal;
    reg [31:0] binary;

    // Commands
    initial begin
        $display("Guia_0101 - Decimal to Binary");

        // Conversions
        // Examples
        decimal = 26; 
        binary = decimal_to_binary(decimal);
        $display("Decimal: %d(10) = Binary: %b(2)", decimal, binary);

        decimal = 53;
        binary = decimal_to_binary(decimal);
        $display("Decimal: %d(10) = Binary: %b(2)", decimal, binary);

        decimal = 713;
        binary = decimal_to_binary(decimal);
        $display("Decimal: %d(10) = Binary: %b(2)", decimal, binary);

        decimal = 213;
        binary = decimal_to_binary(decimal);
        $display("Decimal: %d(10) = Binary: %b(2)", decimal, binary);

        decimal = 365;
        binary = decimal_to_binary(decimal);
        $display("Decimal: %d(10) = Binary: %b(2)", decimal, binary);

        $finish;
    end

    // Converter function
    function [31:0] decimal_to_binary;
        input [31:0] decimal;
        integer i;
        reg [31:0] temp_binary;
        reg [31:0] quotient;

        begin
            quotient = decimal;
            temp_binary = 0;

            // Convert decimal to binary
            i = 0;
            while (quotient > 0) begin
                temp_binary[i] = quotient % 2;
                quotient = quotient / 2;
                i = i + 1;
            end

            decimal_to_binary = temp_binary;
        end
    endfunction
endmodule // Guia_0101
