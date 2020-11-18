#lang racket

; 1. Paramètres optionnels et APPLY

(define l1 '(1 2 (3 4) 5))

(define (member x l &optional test)
  (cond [(equal? l '()) '()]
        [(equal? (car l) x) l]
        [#t (member x (cdr l))] ))
(member 5 l1)