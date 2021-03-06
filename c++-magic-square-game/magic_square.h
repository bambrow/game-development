/***********************************************************

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#pragma once

#include "stdafx.h"
#include "game_base.h"

#ifndef magic_square_h
#define magic_square_h

#include <ostream>
#include <map>

using std::map;
using std::ostream;

const unsigned int M_DIM = 3; // default dimension of MagicSquare
const unsigned int M_MIN = 1; // default minimum number of MagicSquare

/* class of game */
class MagicSquare : public GameBase
{
public:
	/* friend */
	friend ostream& operator<<(ostream &, const MagicSquare &);
	/* constructor */
	MagicSquare() : MagicSquare(M_DIM, M_MIN) {};
	MagicSquare(const unsigned int& d) : MagicSquare(d, M_MIN) {};
	MagicSquare(const unsigned int&, const int&);
	/* game methods */
	/* print the board */
	virtual void print();
	/* detect if the game is successfully over */
	virtual bool done();
	/* detect if the game is over, but not successfully over */
	virtual bool stalemate();
	/* handle a turn where user chooses a valid piece and makes a valid move */
	virtual int turn();
	/* prompt the number from input if valid */	
	virtual void prompt(int&);
	virtual void prompt(unsigned int&);
private:
	/* member variables */
	int sum;
	map<int, game_piece> available;
	const game_piece space = game_piece();
	/* helper methods */
	/* convert the integer entered into string */
	string convert(const int&);
	/* convert the string entered into integer */
	int convert(const string&);
	/* calculate the sum of the values of the piece on the board horizontally */
	int sumHorizontal(const unsigned int&);
	/* calculate the sum of the values of the piece on the board vertically */
	int sumVertival(const unsigned int&);
	/* calculate the sum of the values of the piece on the board diagonally */
	int sumDiagonal(const bool&);
	/* add game_piece to the board */
	bool addPiece(const int&, const unsigned int&, const unsigned int&);
};

/* insertion operator */
ostream& operator<<(ostream &, const MagicSquare &);

#endif
