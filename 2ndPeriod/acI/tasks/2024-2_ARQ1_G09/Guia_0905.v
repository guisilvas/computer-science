// -------------------------
// Guia_095
// Nome: Guilherme Soares Silva
// Matricula: 863485
// -------------------------

module clock(clk);
	output clk;
	reg clk;
	
	initial begin
		clk = 1'b0;
	end
	
	always begin
		#12 clk = ~clk;
	end
endmodule // end clock

module pulse(signal, clock);
	input clock;
	output signal;
	reg signal;

	always @ (posedge clock) begin
		signal = 1'b1;
		#4 signal = 1'b0;
		#4 signal = 1'b1;
		#4 signal = 1'b0;
	end
endmodule // end pulse

module Guia_0905;
	
	wire clock;
	clock clk(clock);

	wire p;
	pulse pls (p, clock);

	initial begin
		$dumpfile("Guia_0905.vcd");
		$dumpvars(1, clock, p);
		#480 $finish;
	end
endmodule
